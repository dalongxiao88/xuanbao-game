@echo off
chcp 65001 >nul
echo ========================================
echo    GitHub 认证配置工具
echo ========================================
echo.
echo 如果推送时遇到认证失败，你需要使用Personal Access Token
echo.
echo 步骤：
echo 1. 访问 https://github.com/settings/tokens
echo 2. 点击 "Generate new token" (classic)
echo 3. 勾选 "repo" 权限
echo 4. 生成并复制token
echo 5. 在推送时使用token作为密码
echo.
echo ========================================
echo.

choice /C 123 /M "选择操作: 1=配置凭据存储 2=测试连接 3=退出"
if errorlevel 3 goto :end
if errorlevel 2 goto :test
if errorlevel 1 goto :config

:config
echo.
echo 配置Git凭据存储...
git config --global credential.helper store
echo 完成！下次推送时输入用户名和token，Git会自动保存
echo.
echo 用户名：dalongxiao88
echo 密码：使用你的Personal Access Token（不是GitHub密码）
echo.
goto :end

:test
echo.
echo 测试GitHub连接...
git ls-remote https://github.com/dalongxiao88/xuanbao-game.git HEAD
if errorlevel 1 (
    echo.
    echo 连接失败！
    echo.
    echo 可能的原因：
    echo 1. 网络问题
    echo 2. 认证失败 - 需要配置Personal Access Token
    echo 3. 仓库地址错误
    echo.
) else (
    echo.
    echo 连接成功！
)
goto :end

:end
echo.
pause
