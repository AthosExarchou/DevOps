# Basic DevOps Concepts and Tools Group Project 2025 (Real Estate)

This application was developed as a group project for the **Basic DevOps Concepts and Tools** course at [Harokopio University of Athens – Dept. of Informatics and Telematics](https://www.dit.hua.gr).

---

## Contents
1. [Overview](#overview)
2. [Technologies / Tools Used](#technologies--tools-used)
3. [Components](#components)
4. [Deployment Options](#deployment-options)
    - [Docker](#1-docker)
    - [Ansible](#2-ansible)
    - [Kubernetes](#3-kubernetes)
5. [CI/CD (Jenkins)](#cicd-jenkins)
6. [Project Structure](#project-structure)
7. [Notes](#notes)
8. [Contact](#contact)
9. [Authors](#authors)
10. [License](#license)

---

## Overview

This project demonstrates a complete **DevOps pipeline** for deploying a Spring Boot-based **real estate management application**.

It covers:
- Infrastructure provisioning
- CI/CD pipelines
- Containerization
- Deployment on **Docker**, **Ansible**, and **Kubernetes** environments
- Integration with **Jenkins** for automation

---

## Technologies / Tools Used

| Tool / Technology | Description                                                    |
|-------------------|----------------------------------------------------------------|
| **Spring Boot**   | Backend application for real estate management                 |
| **PostgreSQL**    | Relational database for data persistence                       |
| **Docker**        | Containerization of application services                       |
| **Docker Compose**| Local environment setup for app and DB                         |
| **Ansible**       | VM provisioning and application deployment                     |
| **Kubernetes**    | Container orchestration with manifests for app & DB            |
| **Jenkins**       | CI/CD pipeline for build, test, image creation, and deployment |
| **Nginx**         | Reverse proxy for exposing the application via HTTP            |

---

## Components

- [`ds-exc-2024`](ds-exc-2024/README.md): Spring Boot application containing business logic and REST APIs.
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

---

## CI/CD (Jenkins)

A Jenkins pipeline (`Jenkinsfile`) should include the following stages:

1. Checkout source code
2. Maven build and unit tests
3. Docker image build and push
4. Deployment via Ansible and/or `kubectl`

---

## Project Structure

```text
DevOps/
├── README.md                   # Main project documentation
├── .gitignore                  # Git ignore rules
├── LICENSE                     # MIT license file
├── report_el/                  # Project report (in greek)
│
├── ds-exc-2024/                # Spring Boot backend application
├── ├── README.md               # Application documentation
│   ├── Dockerfile              # Defines Docker image for the Spring Boot app
│   ├── pom.xml                 # Maven build configuration
│   └── src/                    # Application source code
│       ├── main/               # Production code (Java + resources)
│       └── test/               # Unit and integration tests
│
└── realestate-monorepo/        # Infrastructure as Code (IaC) and deployment configs
    ├── ansible/                # Ansible automation
    │   └── ansible-playground/
    │       ├── playbooks/      # Playbooks (docker.yaml, deploy-all.yaml, spring.yaml, etc.)
    │       ├── group_vars/     # Variables (dbservers, appservers, environment configs)
    │       └── files/          # Static files (docker-compose.yml, templates, configs)
    │
    └── k8s/                    # Kubernetes manifests
        ├── app/                # Deployments, Services, Ingress for the Spring Boot app
        └── db/                 # Deployments, Services, PVCs for PostgreSQL
```

---

## Notes

- Reverse proxy handled by **Nginx** (template available).
- **Systemd service template** available for VM-based deployments.
- **Kubernetes manifests** include secrets, services, deployments, and ingress.
- Multiple environments supported:
  - Docker (local development)
  - Ansible (VM-based deployment)
  - Kubernetes (orchestration and scaling)

---

## Contact

For any issues or contributions, feel free to open a GitHub issue or pull request.

#### Email: realestate2025project@gmail.com
#### Access points:

- Docker: [http://realestate.ip-ddns.com:8080](http://realestate.ip-ddns.com:8080/)
- Jenkins Dashboard: [http://realestate.ip-ddns.com:8180](http://realestate.ip-ddns.com:8180/)
- Kubernetes: [http://realestate.ip-ddns.com:8280](http://realestate.ip-ddns.com:8280/)
- Ansible: [http://realestate.ip-ddns.com:8380](http://realestate.ip-ddns.com:8380/)

---

## Authors

| Student ID   | Name                          |
|--------------|-------------------------------|
| it2022134    | Exarchou Athos                |
| it22150      | Christos Kalamatianos         |

---

## License
This project is licensed under the **MIT License**.
