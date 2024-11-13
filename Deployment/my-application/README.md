# default-helm-chart
Default helm chart like template to deploy anything

# Documentation
## Environment requirments 
- kubernetes cluster
- kubectl (CLI)
- helm (CLI)
- nginx ingress operator
- cert-manager operator
- prometheus operator
- ansible-vault (CLI, optional) 

## Secrets approach
Decryption step should be a part of deploy pipeline. 
Ansible vault demo password: 123456
- to encrypt secret values
```bash
ansible-vault encrypt ./values-secrets.yaml
```
- to decrypt secret vaues
```bash
ansible-vault encrypt ./values-secrets.yaml
```
## Helm cli
- render helm chart
```bash
helm template application . -f ./values-secrets-orig.yaml
```
- install helm chart
```bash
helm install application . -f ./values-secrets-orig.yaml
```
- update deploy
```bash
helm upgrade --install application . -f ./values-secrets-orig.yaml
```
- remove helm chart
```bash
helm uninstall application
```
