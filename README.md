# Infinite Fuel
![Spigot](https://img.shields.io/badge/Spigot-1.21.x-yellow.svg)
![PaperMC](https://img.shields.io/badge/PaperMC-1.21.x-blue.svg)
![Purpur](https://img.shields.io/badge/Purpur-1.21.x-pink.svg)
![Version](https://img.shields.io/badge/Version-1.02-gray.svg)
![MIT License](https://img.shields.io/badge/License-MIT-green.svg)
## Overview
A Spigot plugin for Minecraft 1.20+ that allows furnaces, blast furnaces, and smokers to burn indefinitely when placed over **lava** or a **magma block**.

## Features
- **Infinite fuel:** No fuel required when a furnace is placed over **lava** or a **magma block**.
- **Locked fuel slot:** Players **cannot insert or remove fuel** when the furnace is powered by lava or magma.
- **Custom fuel display:** The fuel slot displays a **magma block** with a configurable name (default: `Infinite Fuel`).
- **Supports all furnace types:** Works with **furnaces, blast furnaces, and smokers**.
- **Configurable settings:** Customize the name of the "infinite fuel" item in the `config.yml` file.

## Requirements
- `Spigot 1.21+` or compatible version.
- `Java 21` or higher

## Installation

1. Download the latest `.jar` file.
2. Place it into your **Spigot/Paper** server's `plugins/` folder.
3. Restart or reload your server.
4. The plugin will automatically generate a `config.yml` file.

## Configuration Files

You can customize the fuel name displayed in the furnace:

```yaml
fuel-name: "Infinite Fuel"
````

After modifying `config.yml`, restart your server.

## How It Works
- Place a furnace, blast furnace, or smoker above lava or a magma block.
- The fuel slot will be locked and automatically display a magma block as "fuel."
- Insert items into the input slot—the furnace will smelt without needing fuel!
- If the furnace is moved or loses its heat source, it will function normally again.

## Fuel Slot Protection
- Players CANNOT add or remove fuel when the furnace is powered by lava/magma.
- The fuel slot displays a magma block named according to your config.yml.

## Permissions
This plugin does not require any permissions. It works automatically when a furnace is placed over a heat source.

## Troubleshooting
- If the plugin is not loading properly, check the server logs for errors. Ensure that all necessary files (`config.yml`) are present in the plugin's data folder.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributions
Feel free to contribute to this plugin by opening issues or submitting pull requests. Please ensure that your changes are well-documented and follow the existing coding style.
