@echo off
chcp 65001 >nul
echo ========================================
echo    GitHub 一键推送工具
echo ========================================
echo.

echo [1/4] 检查Git配置...
git config user.name >nul 2>&1
if errorlevel 1 (
    echo 错误：Git未配置用户信息
    echo 请先运行：git config --global user.name "你的用户名"
    pause
    exit /b 1
)

echo 当前用户：
git config user.name
git config user.email
echo.

echo [2/4] 添加所有文件到暂存区...
git add -A
if errorlevel 1 (
    echo 错误：添加文件失败
    pause
    exit /b 1
)
echo 完成！
echo.

echo [3/4] 提交更改...
set /p commit_msg="请输入提交信息（直接回车使用默认信息）: "
if "%commit_msg%"=="" set commit_msg=更新项目文件 %date% %time%

git commit -m "%commit_msg%"
if errorlevel 1 (
    echo 提示：没有需要提交的更改，或提交失败
    echo.
)
echo.

echo [4/4] 推送到GitHub...
echo 正在推送到远程仓库...
git push origin main
if errorlevel 1 (
    echo.
    echo ========================================
    echo 推送失败！可能的原因：
    echo 1. 网络连接问题
    echo 2. 认证失败（需要Personal Access Token）
    echo 3. 远程仓库有冲突
    echo.
    echo 尝试解决方案：
    echo ========================================
    echo.
    
    choice /C YN /M "是否尝试强制推送（会覆盖远程仓库）"
    if errorlevel 2 goto :end
    if errorlevel 1 goto :force_push
)

echo.
echo ========================================
echo 推送成功！
echo ========================================
goto :end

:force_push
echo.
echo 执行强制推送...
git push -f origin main
if errorlevel 1 (
    echo 强制推送也失败了。
    echo.
    echo 请检查：
    echo 1. 网络连接是否正常
    echo 2. GitHub账号认证是否有效
    echo 3. 查看详细错误信息
) else (
    echo.
    echo ========================================
    echo 强制推送成功！
    echo ========================================
)

:end
echo.
pause
