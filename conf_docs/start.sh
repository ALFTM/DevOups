#!/bin/bash

# Install
sudo apt-add-repository ppa:ansible/ansible
sudo apt-get update
sudo apt-get install ansible vagrant

# Vagrant
vagrant up

# Ansible playbooks
ansible-playbook docker.yml -i platforms/vagrant/inventory.ini --vault-password-file platforms/vagrant/vault_password.txt -vv

ansible-playbook registry.yml -i platforms/vagrant/inventory.ini --vault-password-file platforms/vagrant/vault_password.txt -vv

ansible-playbook swarm.yml -i platforms/vagrant/inventory.ini --vault-password-file platforms/vagrant/vault_password.txt -vv

ansible-playbook zookeeper.yml -i platforms/vagrant/inventory.ini --vault-password-file platforms/vagrant/vault_password.txt -vv

ansible-playbook kafka.yml -i platforms/vagrant/inventory.ini --vault-password-file platforms/vagrant/vault_password.txt -vv

ansible-playbook jenkins.yml -i platforms/vagrant/inventory.ini --vault-password-file platforms/vagrant/vault_password.txt -vv

# Stopping VMs
vagrant halt

# Removing VMs
vagrant destroy