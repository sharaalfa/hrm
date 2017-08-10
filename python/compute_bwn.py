import pandas
import compute_10MAXBWN



betweenness_centrality = pandas.read_csv('/home/sha/Documents/hrm/betweenness_centrality.csv')

compute_10MAXBWN.firstTenValue(betweenness_centrality, 'betweenness_centrality', 'betweenness')