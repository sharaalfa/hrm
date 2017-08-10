import pandas
import compute_10MAXBWN
in_edges_eigenvector_centrality = pandas.read_csv('/home/sha/Documents/hrm/in_edges_eigenvector_centrality.csv')


compute_10MAXBWN.firstTenValue(in_edges_eigenvector_centrality, 'in_edges_eigenvector_centrality', 'in')