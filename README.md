# foresight
## demo
include three services<br />
* spring-boot
* service2
* service3   
## Online demo    
This is [demo](http://133.133.134.117:30543/chaining "Title").    
This is [bookinfo](http://133.133.134.117:30543/productpage "Title").  
This is [zipkin](http://133.133.134.117:30165/ "Title").    
This is [prometheus](http://133.133.134.117:31137/ "Title").   
This is [grafana](http://133.133.134.117:30789/ "Title").
## How to deploy
The version of istio we use is 0.5.0.
### step 1    
<pre><code>cd istio-0.5   
    
export PATH=$PWD/bin:$PATH   
     
kubectl apply -f install/kubernetes/istio.yaml

</code></pre>    
### step 2    
<pre><code>kubectl apply -f zipkin.yaml   
    
kubectl apply -f prometheus.yaml    
     
kubectl apply -f grafana.yaml    

</code></pre>
If you want to use database instead of in-memory-storage,  you can refer to [database](https://github.com/openzipkin/zipkin/tree/master/zipkin-storage).  
Here we use elasticsearch as an example.  
       
<pre><code>kubectl apply -f elasticsearch.yaml 
</code></pre>
Meanwhile, you should modify zipkin.yaml like zipkin-es.yaml and deploy it.   
###all-in-one  
all-in-one.yaml contains all files above. If you want to deploy them simultaneously, use it.   
<pre><code>kubectl apply -f all-in-one.yaml     
</code></pre>
### Samples   
deploy demo service.
<pre><code>kubectl apply -f <(istioctl kube-inject --debug -f demo/demo.yaml)       
</code></pre>
or you can deploy bookinfo service.
<pre><code>kubectl apply -f <(istioctl kube-inject --debug -f samples/bookinfo/kube/bookinfo.yaml)       
</code></pre>
