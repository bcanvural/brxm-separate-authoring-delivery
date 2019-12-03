# Run brXM in minikube

(Tested minikube version: v1.5.2)

Install virtualbox https://www.virtualbox.org/wiki/Downloads

Install minikube https://github.com/kubernetes/minikube
```bash
brew cask install minikube
```
Start minikube with some additional resources

```bash
minikube --memory 8192 --cpus 2 start
```

### One time setup
Setup helm (tested with v3.0.0) (kubernetes package manager) https://github.com/helm/helm
```bash
brew install kubernetes-helm
```

Setup db (from kubernetes directory)

```bash
./setup_dbs.sh
```
This will create brxm repository db and also wpm db for the projects plugin

## Local Development without pushing to public docker registry
* To be able to work with the docker daemon on your mac/linux host use the docker-env command in your shell
```bash
eval $(minikube docker-env)
```
* More info on the above command is at: https://kubernetes.io/docs/setup/minikube#reusing-the-docker-daemon
* Note that you should keep using the same shell
* You have to run this command for every new shell you open

* Now you can build the docker image and tag it at the same time


### Build Authoring image (cms and site)

(Run eval command above so that the images are picked up by minikube)

```bash
mvn clean install && mvn -Pdocker.build,docker.authoring
```

### Build Delivery image (site and platform)

(Run eval command above so that the images are picked up by minikube)

```bash
mvn clean install && mvn -Pdocker.build,docker.delivery
```

### Deploy authoring

From kubernetes directory:

```bash
kubectl create -f authoring-deployment.yaml
```

### Deploy delivery

Make sure authoring is deployed first. Then, from kubernetes directory:

```bash
kubectl create -f delivery-deployment.yaml
```

### Open local port to authoring or delivery port within cluster

get available pods:

```bash
kubectl get pods
```

Forward your localhost directly to the pod:

````bash
kubectl port-forward <pod_id_here> 8080:8080
````

Then visit http://localhost:8080/site (or cms)

### Setup debugger:

get pod id and run:

```bash
kubectl port-forward <pod_id_here> 5005:5005
```

Then connect with remote debugger at port 5005


# Run brXM with docker-compose instead of minikube

From docker-compose directory, run:

```bash
docker-compose up
```






