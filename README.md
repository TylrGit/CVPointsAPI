[shields.io](https://shields.io/)
<p align="center">
  <img alt="Language: Java" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img alt="Platform: Spigot/Paper" src="https://img.shields.io/badge/Spigot%2FPaper-F7931E?style=for-the-badge&logo=minecraft&logoColor=white" />
  <img alt="API: REST" src="https://img.shields.io/badge/REST_API-0055DA?style=for-the-badge&logo=postman&logoColor=white" />
  <img alt="Version: 1.21.8" src="https://img.shields.io/badge/version-1.21.8-blue?style=for-the-badge" />
</p>

# CVPointsAPI

A Minecraft Spigot/Paper plugin that provides a REST API for managing player permissions and tokens by hooking into other plugins, namely Vault API and BetonQuest.

## 🖼️Preview

### Console Logs

```text
[INFO]: [CVPointsAPI] Loading server plugin CVPointsAPI v1.21.8
[INFO]: [CVPointsAPI] Enabling CVPointsAPI v1.21.8
[INFO]: [CVPointsAPI] REST server started on port 8080
[INFO]: [CVPointsAPI] CVTokenAPI enabled successfully!
```

## 🛠️Tech Stack

**Stack:** Java, Gson, Spigot API

**Tools:** Insomnia, Maven

### Features:

- Query `/status` with `POST' requests for players' permission & token balances
- Manage permission & token balances via `POST` requests to `/status`


## 📥Installation

Download project, add to your plugins folder along with the required plugin dependencies:

- Vault
- BetonQuest

#### Environment Variables

Change the following key in `src/main/resources/config.yml`:

```yaml
# Security key used to authenticate incoming REST API requests
private-key: "YOUR_SECURE_PRIVATE_KEY_HERE"
```
