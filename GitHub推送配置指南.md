# GitHub推送配置指南

## 当前Git配置信息
- **用户名**: dalongxiao88
- **邮箱**: 2704986524@qq.com
- **远程仓库**: https://github.com/dalongxiao88/xuanbao-game.git
- **当前分支**: main

## 问题诊断

当前仓库显示大量文件处于"deleted"状态，这可能导致推送失败。

## 解决方案

### 方案1：重置并重新提交（推荐）

如果你想保留当前的文件状态，执行以下步骤：

```bash
# 1. 添加所有更改（包括删除的文件）
git add -A

# 2. 提交更改
git commit -m "更新项目文件"

# 3. 推送到GitHub
git push origin main
```

### 方案2：如果需要强制推送

如果远程仓库有冲突，可以使用强制推送（**注意：这会覆盖远程仓库**）：

```bash
# 1. 添加所有更改
git add -A

# 2. 提交更改
git commit -m "重新整理项目文件"

# 3. 强制推送
git push -f origin main
```

### 方案3：配置Git凭据（解决认证问题）

如果推送时提示认证失败：

```bash
# 使用GitHub Personal Access Token
# 1. 访问 https://github.com/settings/tokens
# 2. 生成新的token（勾选repo权限）
# 3. 使用token作为密码推送

# 或者配置凭据存储
git config --global credential.helper store
```

### 方案4：检查网络连接

如果推送超时或连接失败：

```bash
# 测试GitHub连接
ssh -T git@github.com

# 或者使用HTTPS测试
git ls-remote https://github.com/dalongxiao88/xuanbao-game.git
```

## 常见错误及解决方法

### 错误1：认证失败 (Authentication failed)
**解决方法**：
- 使用Personal Access Token代替密码
- 或配置SSH密钥

### 错误2：推送被拒绝 (Push rejected)
**解决方法**：
```bash
# 先拉取远程更改
git pull origin main --rebase

# 然后再推送
git push origin main
```

### 错误3：网络超时
**解决方法**：
- 检查网络连接
- 如果在中国大陆，可能需要配置代理
- 增加超时时间：
```bash
git config --global http.postBuffer 524288000
git config --global http.lowSpeedLimit 0
git config --global http.lowSpeedTime 999999
```

## 使用自动化脚本

我已经为你创建了自动推送脚本，直接运行：
- `一键推送.bat` - 自动添加、提交并推送

## 需要帮助？

如果以上方法都不行，请提供具体的错误信息，我会帮你进一步诊断。
