@echo off
chcp 65001 >nul
echo ========================================
echo    Git 配置诊断工具
echo ========================================
echo.

echo [检查1] Git版本
git --version
echo.

echo [检查2] 用户配置
echo 用户名：
git config user.name
echo 邮箱：
git config user.email
echo.

echo [检查3] 远程仓库配置
git remote -v
echo.

echo [检查4] 当前分支
git branch
echo.

echo [检查5] 仓库状态
git status --short
echo.

echo [检查6] 最近的提交
git log --oneline -5
echo.

echo [检查7] 测试GitHub连接
echo 正在测试连接到GitHub...
git ls-remote https://github.com/dalongxiao88/xuanbao-game.git HEAD
if errorlevel 1 (
    echo 连接失败！可能的原因：
    echo 1. 网络问题
    echo 2. 仓库不存在或无权限
    echo 3. 认证失败
) else (
    echo 连接成功！
)
echo.

echo ========================================
echo 诊断完成
echo ========================================
echo.
echo 如果发现问题，请参考 GitHub推送配置指南.md
echo.
pause
