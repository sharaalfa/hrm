import json

import networkx as nx
import numpy as np
import pandas as pd
from networkx.readwrite import json_graph
from plot_graph import *
from scipy.sparse import *

#path_to_centralities = ".\centralities"
path_to_centralities = "/home/sha/Documents/hrm/"

def get_email_dep_dict():
    email_dep_table = pd.read_csv('/home/sha/Documents/hrm/emails_departments.csv', header=None, encoding='utf-8')
    email_dep_dict = dict(zip(email_dep_table[0].values, email_dep_table[1].values))
    return email_dep_dict

def get_email_mrf_dict():
    email_dep_table = pd.read_csv('/home/sha/Documents/hrm/emails_departments.csv', header=None, encoding='utf-8')
    email_dep_table[1] = email_dep_table[1].apply(func=lambda x: x.split(' \ ')[0])
    email_dep_dict = dict(zip(email_dep_table[0].values, email_dep_table[1].values))
    return email_dep_dict

def __adjacency_matrix(sources, destinations, email_target_dict):
    '''
    массивы массивов
    '''
    outside = 'Внешние'
    unique_targets = np.append(np.unique(list(email_target_dict.values())), outside)
    unique_targets_dict = dict(zip(unique_targets, np.arange(0, unique_targets.shape[0])))
    emails = email_target_dict.keys()

    ncols, nrows, dat = [], [], []
    for i, s_email in enumerate(sources):
        added_targets = []
        for d_email in destinations[i]:
            if d_email in emails:
                if email_target_dict[d_email] not in added_targets:
                    added_targets.append(email_target_dict[d_email])
                    if s_email in emails:
                        nrows.append(unique_targets_dict[email_target_dict[s_email]])
                    else:
                        nrows.append(unique_targets_dict[outside])
                    ncols.append(unique_targets_dict[email_target_dict[d_email]])
                    dat.append(1)
            else:
                if outside not in added_targets:
                    added_targets.append(outside)
                    if s_email in emails:
                        nrows.append(unique_targets_dict[email_target_dict[s_email]])
                    else:
                        nrows.append(unique_targets_dict[outside])
                    ncols.append(unique_targets_dict[outside])
                    dat.append(1)

    size = len(unique_targets)
    adj_matrix = csr_matrix((dat, (nrows, ncols)), shape=(size, size))
    return adj_matrix

def get_adjacency_matrix(email_target_dict, filename):

    reader = pd.read_csv(filename, header=None, names=['time', 'source', 'destination', 'id'],
                         usecols=['source', 'destination'], infer_datetime_format=True, iterator=True, chunksize=250000)

    adjacency_matrix = None
    for i, chunk in enumerate(reader):
        sources = chunk.source.apply(func=lambda x: x.strip().lower()).values
        destinations = chunk.destination.apply(func=lambda x: x.strip().lower().split(' ')).values
        adj_matrix_for_chunk = __adjacency_matrix(sources, destinations, email_target_dict)
        if i == 0:
            adjacency_matrix = adj_matrix_for_chunk
        else:
            adjacency_matrix += adj_matrix_for_chunk

    return adjacency_matrix

def compute_and_save_centralities(matrix, departments):
    G = nx.from_scipy_sparse_matrix(matrix, create_using=nx.DiGraph())
    in_edges_eig_centrality = nx.eigenvector_centrality_numpy(G)
    out_edges_eig_centrality = nx.eigenvector_centrality_numpy(G.reverse())
    betweenness_centrality = nx.betweenness_centrality(G)
    centralities_deps = pd.DataFrame(data={'department':departments,
                                           'in_edges_eigenvector_centrality':np.round(list(in_edges_eig_centrality.values()), 6),
                                           'out_edges_eigenvector_centrality':np.round(list(out_edges_eig_centrality.values()), 6),
                                           'betweenness_centrality':np.round(list(betweenness_centrality.values()), 8)})
    centralities_deps.department.to_csv(path_to_centralities + 'departaments.csv', index=False, header=True)
    centralities_deps.in_edges_eigenvector_centrality.to_csv(path_to_centralities + 'in_edges_eigenvector_centrality.csv',
                                                             index=False, header=True)
    centralities_deps.out_edges_eigenvector_centrality.to_csv(path_to_centralities + 'out_edges_eigenvector_centrality.csv',
                                                              index=False, header=True)
    centralities_deps.betweenness_centrality.to_csv(path_to_centralities + 'betweenness_centrality.csv',
                                                    index=False, header=True)


def getResume(a):
    email_dep_dict = get_email_dep_dict()
    outside = 'Внешние'
    unique_deps = np.append(np.unique(list(email_dep_dict.values())), outside)
    matrix = get_adjacency_matrix(email_dep_dict, a)
    compute_and_save_centralities(matrix, unique_deps)

    email_mrf_dict = get_email_mrf_dict()
    outside = 'Внешние'
    unique_mrf = np.append(np.unique(list(email_mrf_dict.values())), outside)
    matrix = get_adjacency_matrix(email_mrf_dict, a)

    G = nx.from_numpy_matrix(matrix.todense(), create_using=nx.DiGraph())
    with open('/home/sha/Documents/hrm/networkdata.json', 'w') as outfile:
        outfile.write(json.dumps(json_graph.node_link_data(G)))

    labels = []
    for i in range(len(unique_mrf)):
        label = [unique_mrf[i]]
        label.append('отправленные: ' + str(np.sum(matrix[i, :]) - matrix[i,i]))
        label.append('полученные: ' + str(np.sum(matrix[:, i]) - matrix[i,i]))
        labels.append(label)

    plot_graph(matrix.todense(), labels, title='Макрорегиональные филиалы', filename='/home/sha/Documents/hrm/graph.html')




