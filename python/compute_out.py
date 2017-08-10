import pandas
import compute_10MAXBWN
out_edges_eigenvector_centrality = pandas.read_csv('/home/sha/Documents/hrm/out_edges_eigenvector_centrality.csv')
compute_10MAXBWN.firstTenValue(out_edges_eigenvector_centrality, 'out_edges_eigenvector_centrality', 'out')