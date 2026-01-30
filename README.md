# 玄宝端游戏项目

这是一个游戏服务器和客户端项目。

## 项目结构

- `GameClient/` - 游戏客户端
- `Gameserver/` - 游戏服务器（Java项目）

## Git推送工具

为了方便管理代码，我提供了以下工具：

### 🚀 快速开始

1. **快速修复并推送.bat** - ⭐推荐！一键修复并推送（适合首次使用）
2. **诊断Git配置.bat** - 检查Git配置和连接状态
3. **一键推送.bat** - 自动添加、提交并推送代码
4. **配置GitHub认证.bat** - 配置GitHub认证（如果推送失败）
5. **重置仓库.bat** - 重置Git状态（谨慎使用）

### 📖 使用说明

#### 第一次推送（推荐）

直接运行 `快速修复并推送.bat`，它会自动处理所有问题并推送到GitHub。

#### 手动推送

1. 运行 `诊断Git配置.bat` 检查配置
2. 如果提示认证问题，运行 `配置GitHub认证.bat`
3. 运行 `一键推送.bat` 推送代码

#### 日常推送

直接运行 `一键推送.bat` 即可

### ⚠️ 常见问题

**问题1：推送失败，提示认证错误**

解决方法：
1. 访问 https://github.com/settings/tokens
2. 生成新的Personal Access Token（勾选repo权限）
3. 运行 `配置GitHub认证.bat`
4. 推送时使用token作为密码

**问题2：推送被拒绝**

解决方法：
```bash
git pull origin main --rebase
git push origin main
```

**问题3：网络超时**

解决方法：
- 检查网络连接
- 如果在中国大陆，可能需要配置代理
- 或使用VPN

### 📚 详细文档

查看 `GitHub推送配置指南.md` 获取更多信息

## Git配置信息

- **用户名**: dalongxiao88
- **邮箱**: 2704986524@qq.com
- **远程仓库**: https://github.com/dalongxiao88/xuanbao-game.git
- **主分支**: main

## 开发环境

- Java 8+
- Git

## 许可证

私有项目
