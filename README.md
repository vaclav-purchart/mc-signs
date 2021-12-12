# McSigns

The McSigns is a simple signs plugin which allows teleport and repair signs for the Spigot Minecraft server (bukkit compatible). If you want to compile it yourself run maven build.

 ![McSigns](/warp.png)

Tested in Spigot versions **1.18.0**.

# Usage
## Signs
Simply put command on the first line of the sign in brackets and arguments on the second line. Supported commands: `warp`, `setwarp`, `loc`, `repair` (see the section below).

Example sign text:
```
[warp]
home
```
> will teleport you to a "home" location once you right-click the sign.

## Available commands:

- `warp` - usage: `/warp <warp name>`
> Teleport your player to "warp name" location (if it is defined).
- `warps` - usage `/warps` 
> Display list of created warps (stored in plugin's directory).
- `setwarp` (alias `loc`) - usage: `/setwarp <warp name>`
> It will create a new warp location. If the location already exists it will be overwritten. You can later teleport player to this location by `warp` command.
- `repair` - usage `/repair`
> Repair item in your hand.

# Installation

Copy jar file into your server plugin directory and reload/restart the server.

# License

The MIT License (MIT)

Copyright (c) 2021 Vaclav Purchart

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
