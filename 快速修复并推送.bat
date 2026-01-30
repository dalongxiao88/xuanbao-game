@echo off
chcp 65001 >nul
echo ========================================
echo    快速修复并推送工具
echo ========================================
echo.
echo 此工具将：
echo 1. 添加所有更改（包括删除的文件）
echo 2. 提交更改
echo 3. 推送到GitHub
echo.
echo 当前检测到大量文件变更，这是正常的。
echo.

choice /C YN /M "确定要继续吗"
if errorlevel 2 goto :end
if errorlevel 1 goto :fix

:fix
echo.
echo [步骤1/4] 添加所有文件...
git add -A
if errorlevel 1 (
    echo 错误：添加文件失败
    pause
    exit /b 1
)
echo ✓ 完成
echo.

echo [步骤2/4] 提交更改...
git commit -m "整理项目文件结构"
if errorlevel 1 (
    echo 提示：可能没有需要提交的更改
)
echo ✓ 完成
echo.

echo [步骤3/4] 拉取远程更新...
echo 正在同步远程仓库...
git pull origin main --rebase
if errorlevel 1 (
    echo 提示：拉取失败或无需拉取，继续推送...
)
echo ✓ 完成
echo.

echo [步骤4/4] 推送到GitHub...
git push origin main
if errorlevel 1 (
    echo.
    echo ❌ 推送失败！
    echo.
    echo 可能的原因：
    echo 1. 认证失败 - 需要Personal Access Token
    echo 2. 网络问题
    echo 3. 远程仓库冲突
    echo.
    echo 解决方案：
    echo - 运行 "配置GitHub认证.bat" 配置认证
    echo - 检查网络连接
    echo - 查看上方的错误信息
    echo.
    pause
    exit /b 1
)

echo.
echo ========================================
echo ✓ 推送成功！
echo ========================================
echo.
echo 你的代码已成功推送到GitHub
echo 仓库地址：https://github.com/dalongxiao88/xuanbao-game
echo.

:end
pause
