# Better Ping Display+

[![](https://img.shields.io/modrinth/dt/better-ping-display-plus?style=for-the-badge&logo=modrinth&logoColor=rgb(27%2C%20217%2C%20106)&label=Downloads&color=rgb(27%2C%20217%2C%20106))](https://modrinth.com/mod/better-ping-display-plus)


A [Fabric](https://fabricmc.net/) mod for Minecraft to add improvements to [Better Ping Display](https://github.com/vladmarica/better-ping-display-fabric) by [vladmarica](https://github.com/vladmarica).

![](https://raw.githubusercontent.com/StarSNG25/better-ping-display-plus/refs/heads/1.21.x-fabric/assets/in-game-ping-preview.png)

This is a client-side mod. The server doesn't need to have it installed. It works even when playing on vanilla servers.

## Feature
Currently, there's only one feature in this addon mod, which is to display null (0ms) ping as the set placeholder, where the default value is `N/A`. This covers players who just joined and the ping has not been reported yet, or Bedrock players on Geyser servers.

## Configuration
This mod's config file is `better-ping-display-plus.json`. It contains the following options:

| Option  | Default Value  | Description  |
|---|---|---|
| nullPingPlaceholder  | `N/A` | The placeholder for ping of 0ms. |
| nullPingPlaceholderColor  | `gray` | The color for the null ping placeholder. |

Optionally, this mod also supports in-game configuration with [Mod Menu](https://modrinth.com/mod/modmenu) and [YACL](https://modrinth.com/mod/yacl) installed.

![](https://raw.githubusercontent.com/StarSNG25/better-ping-display-plus/refs/heads/1.21.x-fabric/assets/in-game-configuration.png)

## Supported Minecraft Versions
* **1.21.x**

## Requirements
* [Fabric](https://fabricmc.net/)
