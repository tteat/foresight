# foresight
## demo
include three services<br />
* spring-boot
* service2
* service3
## yaml
istio、prometheus、zipkin、grafana    
## Online demo    
This is [demo](http://118.190.46.58:31954/ "Title").    
This is [zipkin](http://118.190.46.58:31070/ "Title").    
This is [prometheus](http://118.190.46.58:31137/ "Title").   
This is [grafana](http://118.190.46.58:31964/ "Title").
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
### step 3    
deploy demo service.
<pre><code>kubectl apply -f <(istioctl kube-inject --debug -f demo/demo.yaml)   
    
</code></pre>
