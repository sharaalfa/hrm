import numpy as np
import networkx as nx
import plotly
import plotly.plotly as py
from plotly.graph_objs import *

def scatter_nodes(G, pos, adj_matrix, labels=None, color=None, size=25, opacity=1):
    # pos is the dict of node positions
    # labels is a list  of labels of len(pos), to be displayed when hovering the mouse over the nodes
    # color is the color for nodes. When it is set as None the Plotly default color is used
    # size is the size of the dots representing the nodes
    # opacity is a value between [0,1] defining the node color opacity
    L=len(pos)
    trace = Scatter(x=[], y=[],  text=[], mode='markers', hoverinfo='text+text', 
                    marker=Marker(
                                    showscale=False,
                                    # colorscale options
                                    # 'Greys' | 'Greens' | 'Bluered' | 'Hot' | 'Picnic' | 'Portland' |
                                    # Jet' | 'RdBu' | 'Blackbody' | 'Earth' | 'Electric' | 'YIOrRd' | 'YIGnBu'
                                    colorscale='YIGnBu',
                                    reversescale=True,
                                    color=[],
                                    size=size,
                                    line=dict(width=2)
                                )
                   )
    for node, adjacencies in enumerate(G.adjacency_list()):
        trace['marker']['color'].append(len(adjacencies))
        node_info = '<br>'.join(labels[node])
        trace['text'].append(node_info)
        
    for k in range(L):
        trace['x'].append(pos[k][0])
        trace['y'].append(pos[k][1])
    return trace

def scatter_edges(G, pos, line_color=None, line_width=1):
    trace = Scatter(x=[], y=[], mode='lines')
    for edge in G.edges():
        trace['x'] += [pos[edge[0]][0],pos[edge[1]][0], None]
        trace['y'] += [pos[edge[0]][1],pos[edge[1]][1], None]  
        trace['hoverinfo']='none'
        trace['line']['width']=line_width
        if line_color is not None: 
            trace['line']['color']=line_color
        else:
            trace['line']['color']='#888'
    return trace

def make_annotations(pos, text, font_size=14, font_color='rgb(25,25,25)'):
    L=len(pos)
    if len(text)!=L:
        raise ValueError('The lists pos and text must have the same len')
    annotations = Annotations()
    for k in range(L):
        annotations.append(
            Annotation(
                text=text[k], 
                x=pos[k][0], y=pos[k][1],
                xref='x1', yref='y1',
                font=dict(color= font_color, size=font_size),
                showarrow=False)
        )
    return annotations

def plot_graph(adj_matrix, labels, title, filename='graph', width=1000, height=600):
    '''
    создание графа
    '''
    
    G = nx.from_numpy_matrix(adj_matrix, create_using=nx.DiGraph())
    pos = nx.circular_layout(G)
    
    trace1 = scatter_edges(G, pos)
    trace2 = scatter_nodes(G, pos, adj_matrix, labels)
    
    axis=dict(
        showline=False,
        zeroline=False,
        showgrid=False,
        showticklabels=False,
        title='' 
    )
    layout=Layout(
        title=title,  
        font=Font(),
        showlegend=False,
        autosize=False,
        width=width,
        height=height,
        xaxis=XAxis(axis),
        yaxis=YAxis(axis),
        margin=dict(b=20,l=5,r=5,t=40),
        hovermode='closest'
    )
    
    data=[trace1, trace2]
    fig = Figure(data=data, layout=layout)
    fig['layout'].update(annotations=make_annotations(pos, [str(k) for k in range(len(pos))]))  
    plotly.offline.plot(fig, filename=filename)