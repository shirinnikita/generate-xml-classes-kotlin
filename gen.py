import argparse
import jinja2
import os

import xml.etree.ElementTree as ET
from collections import namedtuple, Counter


# https://stackoverflow.com/questions/32546622/suppress-namespace-in-elementtree
def stripNs(el):
    if el.tag.startswith("{"):
        el.tag = el.tag.split('}', 1)[1] 
    for k in el.attrib.keys():
        if k.startswith("{"):
            k2 = k.split('}', 1)[1]
            el.attrib[k2] = el.attrib[k]
            del el.attrib[k]
    for child in el:
        stripNs(child)


def add_children(node):
    occurances = Counter(map(lambda x: x.tag, node.getchildren()))
    attrs = []
    for child, n in occurances.items():
        if n == 1:
            attrs.append({
                'name': child,
                'type': child,
            })
        else:
            attrs.append({
                'name': child,
                'type': 'List<{}>'.format(child),
            })
    return attrs


def make_class(node):
    return {
        'name': node.tag,
        'attrs': [
            {
                'name': attr,
                'type' : 'String'
            }
            for attr in node.attrib.keys()
        ] + add_children(node)
    }


parser = argparse.ArgumentParser()
parser.add_argument("input", help="location of .xml file")
parser.add_argument("output", default='xmlorm.kt', help="location of .kt file")
args = parser.parse_args()

tree = ET.parse(args.input)
stripNs(tree.getroot())
classes = {}

for elem in tree.iter():
    tmp = make_class(elem)
    classes[tmp['name']] = tmp
    
env = jinja2.Environment(loader=jinja2.FileSystemLoader(''))
generated = env.get_template('kt.tmpl').render({'classes': classes.values()})
with open(args.output, 'w') as f:
    f.write(generated)
    
print('result saved to {}'.format(args.output))
