# Run brXM in minikube

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
Setup helm (kubernetes package manager) https://github.com/helm/helm
```bash
brew install kubernetes-helm
```

Setup tiller (from kubernetes directory, run the following)
```bash
./tiller_setup.sh
```

Now you can install anything that is listed here: https://hub.kubeapps.com/

* Install Postgresql: https://hub.kubeapps.com/charts/bitnami/postgresql

* Take note of the output as it is useful. It tells you how to find out the db password etc. Update the db password in 
kubernetes/authoring-deployment.yaml and kubernetes/delivery-deployment.yaml

* Connect to the db and create a table called "repositoryDS"
* One way to connect to db would be to forward your localhost port to the db pod.
##### How to connect to db
get available pods:

```bash
kubectl get pods
```

Forward your localhost directly to the pod:

````bash
kubectl port-forward <db_pod_id_here> 5432:5432
````

Then connect to db by a postgresql client (localhost:5432)


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
mvn clean install && mvn -Pdocker.build.docker.delivery
```

### Deploy authoring

From kubernetes directory:

```bash
kubectl create -f authoring-deployment.yaml
```

### Deploy delivery

From kubernetes directory:

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






