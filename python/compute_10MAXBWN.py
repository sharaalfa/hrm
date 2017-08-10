import pandas
import xml.etree.cElementTree as ET


departments = pandas.read_csv('/home/sha/Documents/hrm/departaments.csv', encoding="UTF-8")

def firstTenValue(data,header, fileName):
    p = data[header].sort_values()
    root = ET.Element("root")
    for i in range(1, 11):
        doc = ET.SubElement(root, "doc" + str(i))
        ET.SubElement(doc, "number"). \
            text = str(p[-10:].index.values[10 - i])
        ET.SubElement(doc, "department"). \
            text = str(departments.loc[p[-10:].index.values[10 - i]].values)
        ET.SubElement(doc, "centrality"). \
            text = str(data[header].loc[p[-10:].index.values[10 - i]])
    tree = ET.ElementTree(root)
    tree.write("/home/sha/Documents/hrm/" + fileName + ".xml")
