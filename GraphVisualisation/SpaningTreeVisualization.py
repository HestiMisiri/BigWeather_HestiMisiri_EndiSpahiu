import networkx as nx
import matplotlib.pyplot as plt

def read_graph_with_explicit_buckets(filename):
    with open(filename, 'r') as file:
        parts = file.readline().strip().split()
        num_nodes = int(parts[0])
        num_edges = int(parts[1])
        parts = file.readline().strip().split()
        bucket_nodes = list(map(int, parts[0:]))

        edges = set()
        for _ in range(num_edges):
            u, v = map(int, file.readline().strip().split())
            edges.add(tuple(sorted((u, v))))  # undirected

    all_nodes = set(range(1, num_nodes + 1))
    normal_nodes = sorted(list(all_nodes - set(bucket_nodes)))

    return num_nodes, edge_cost, bucket_nodes, normal_nodes, edges

def build_forest_from_components(bucket_nodes, normal_nodes, edges):
    G = nx.Graph()
    G.add_edges_from(edges)

    trees = []
    for component in nx.connected_components(G):
        subgraph = G.subgraph(component).copy()

        # Assign weight priority (bucket-normal gets lower weight)
        for u, v in subgraph.edges():
            if (u in bucket_nodes and v in normal_nodes) or (v in bucket_nodes and u in normal_nodes):
                subgraph[u][v]['weight'] = 0
            else:
                subgraph[u][v]['weight'] = 1

        tree = nx.minimum_spanning_tree(subgraph, weight='weight')
        trees.append(tree)

    return trees

def visualize_forest(trees, bucket_nodes, normal_nodes):
    combined_graph = nx.Graph()
    for tree in trees:
        combined_graph.add_edges_from(tree.edges())

    pos = nx.spring_layout(combined_graph, seed=42)

    nx.draw_networkx_nodes(combined_graph, pos, nodelist=bucket_nodes, node_color='skyblue', label='Bucket Nodes')
    nx.draw_networkx_nodes(combined_graph, pos, nodelist=normal_nodes, node_color='lightgreen', label='Normal Nodes')
    nx.draw_networkx_edges(combined_graph, pos)
    nx.draw_networkx_labels(combined_graph, pos)

    plt.title("Forest of MSTs (One Tree per Connected Component)")
    plt.legend()
    plt.axis('off')
    plt.show()

# ==== MAIN ====
filename = input("Enter the file name: ")
num_nodes, edge_cost, bucket_nodes, normal_nodes, given_edges = read_graph_with_explicit_buckets(filename)

# Build MSTs per component (forest)
forest = build_forest_from_components(bucket_nodes, normal_nodes, given_edges)

# Visualize the result
visualize_forest(forest, bucket_nodes, normal_nodes)
