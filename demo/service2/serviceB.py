
from flask import Flask
from flask import request
import requests
app = Flask(__name__)

@app.route('/')
def hello_world():
  responses = []
  for i in xrange(1):
    headers = {}
    headers = getForwardHeaders(request);
    print(headers)
    r = requests.get('http://service3:9088', headers=headers)
    responses.append(r.text)
  return ", ".join(responses)

def getForwardHeaders(request):
    headers = {}

    incoming_headers = [ 'x-request-id',
                         'x-b3-traceid',
                         'x-b3-spanid',
                         'x-b3-parentspanid',
                         'x-b3-sampled',
                         'x-b3-flags',
                         'x-ot-span-context'
    ]

    for ihdr in incoming_headers:
        val = request.headers.get(ihdr)
        if val is not None:
            headers[ihdr] = val
            #print "incoming: "+ihdr+":"+val

    return headers

if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True, port=9088)
