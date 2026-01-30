@echo off
chcp 65001 >nul
echo ========================================
echo    重置Git仓库工具
echo ========================================
echo.
echo 警告：此操作将重置所有未提交的更改！
echo.
choice /C YN /M "确定要继续吗"
if errorlevel 2 goto :end
if errorlevel 1 goto :reset

:reset
echo.
echo [1/3] 清理暂存区...
git reset HEAD
echo.

echo [2/3] 重新添加所有文件...
git add -A
echo.

echo [3/3] 显示当前状态...
git status
echo.

echo ========================================
echo 重置完成！
echo ========================================
echo.
echo 现在你可以：
echo 1. 运行 "一键推送.bat" 来提交并推送
echo 2. 或手动执行：
echo    git commit -m "你的提交信息"
echo    git push origin main
echo.

:end
pause
