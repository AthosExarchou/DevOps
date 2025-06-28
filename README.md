# Basic DevOps concepts and tools Group Project 2025

This application was developed as a group project for the **Basic DevOps Concepts and Tools** course at [Harokopio University of Athens – Dept. of Informatics and Telematics](https://www.dit.hua.gr).

## Real Estate

This project demonstrates a complete DevOps pipeline for deploying a Spring Boot-based real estate application. It includes infrastructure provisioning, CI/CD pipelines, containerization, and deployment on Docker and Kubernetes environments using Ansible.

---

## Technologies / Tools Used

| Tool / Technology | Description                                                    |
|-------------------|----------------------------------------------------------------|
| **Spring Boot**   | Backend application for real estate management                 |
| **PostgreSQL**    | Relational database for data persistence                       |
| **Docker**        | Containerization of application services                       |
| **Docker Compose**| Local environment setup for app and DB                         |
| **Ansible**       | Provisioning of VMs and Docker                                 |
| **Kubernetes**    | Container orchestration with manifests for app & DB            |
| **Jenkins**       | CI/CD pipeline for build, test, image creation, and deployment |
| **Nginx**         | Reverse proxy for exposing the application via HTTP            |

---

## Components

- `ds-exc-2024`: Spring Boot application containing business logic and APIs.
- `realestate-monorepo`: Infrastructure automation scripts and Kubernetes manifests.

---

## Deployment Options

### 1. **Docker**

Run locally with:

```bash
    ansible-playbook playbooks/docker.yaml
```

### 2. **Ansible**

Provision the app and DB on separate virtual machines:
```bash
    ansible-playbook playbooks/deploy-all.yaml --skip-tags dropdb
```

### **3. Kubernetes**

Apply the manifests:
```bash
    alias k='microk8s.kubectl'
    cd realestate-monorepo/k8s
    k apply -f db/
    k apply -f app/
```

### CI/CD (Jenkins)

A Jenkins pipeline (Jenkinsfile) should include the following stages:

1. Checkout source code
2. Maven build and unit tests
3. Docker image build and push
4. Deployment via Ansible or/and kubectl

### Project Structure

```text
DevOps-test/
├── ds-exc-2024/                # Spring Boot app
│   ├── Dockerfile
│   └── src/
└── realestate-monorepo/
    ├── ansible/
    │   └── ansible-playground/
    │       ├── playbooks/
    │       ├── group_vars/
    │       └── files/
    └── k8s/
        ├── app/
        └── db/
```

### Notes

- Reverse proxy handled by Nginx (template available).
- Systemd template available for Spring Boot deployment on VMs.
- Kubernetes manifests include secrets, services, deployments, ingress etc.


### Contact

For any issues or contributions, feel free to open a GitHub issue or pull request.

For support or inquiries, please contact us at:
#### Email: realestate2025project@gmail.com
#### Website access points:
- Docker: [http://realestate.ip-ddns.com:8080](http://realestate.ip-ddns.com:8080/)
- Jenkins Dashboard: [http://realestate.ip-ddns.com:8180](http://realestate.ip-ddns.com:8180/)
- Kubernetes: [http://realestate.ip-ddns.com:8280](http://realestate.ip-ddns.com:8280/)
- Ansible: [http://realestate.ip-ddns.com:8380](http://realestate.ip-ddns.com:8380/)


## Authors

| Student ID   | Name                          |
|--------------|-------------------------------|
| it2022134    | Exarchou Athos                |
| it22150      | Christos Kalamatianos         |


### License
MIT License

