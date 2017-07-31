import pandas
import xml.etree.cElementTree as ET
import time


in_edges_eigenvector_centrality = pandas.read_csv('/home/sha/Documents/hrm/in_edges_eigenvector_centrality.csv')

out_edges_eigenvector_centrality = pandas.read_csv('/home/sha/Documents/hrm/out_edges_eigenvector_centrality.csv')

betweenness_centrality = pandas.read_csv('/home/sha/Documents/hrm/betweenness_centrality.csv')


# Формирование XML файла
for i in range(0,2991):
    root = ET.Element("root")
    doc = ET.SubElement(root, "doc")
    ET.SubElement(doc, "betweenness"). \
        text=str(betweenness_centrality['betweenness_centrality'][i])
    ET.SubElement(doc, "departments"). \
        text=str(i)
    ET.SubElement(doc, "out"). \
        text=str(out_edges_eigenvector_centrality['out_edges_eigenvector_centrality'][i])
    ET.SubElement(doc, "in"). \
        text=str(in_edges_eigenvector_centrality['in_edges_eigenvector_centrality'][i])
    tree = ET.ElementTree(root)
    tree.write("/home/sha/Documents/hrm/temp.xml")
    time.sleep(0.5)



