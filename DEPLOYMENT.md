# Docker + Nginx 部署指南

## 🚀 快速开始

### 方式1：使用 Docker Compose（推荐）

```bash
# 构建并启动
docker-compose up -d

# 查看日志
docker-compose logs -f

# 停止服务
docker-compose down

# 重新构建
docker-compose up -d --build
```

访问：http://localhost:8080

### 方式2：使用 Docker 命令

```bash
# 构建镜像
docker build -t ai-assistant .

# 运行容器
docker run -d -p 8080:80 --name ai-assistant ai-assistant

# 查看日志
docker logs -f ai-assistant

# 停止容器
docker stop ai-assistant

# 删除容器
docker rm ai-assistant
```

访问：http://localhost:8080

## 📋 配置说明

### API 密钥配置

由于是纯前端项目，API密钥会暴露在客户端。有两种配置方式：

#### 方式A：构建时注入（推荐用于生产）

修改 `docker-compose.yml`：

```yaml
services:
  ai-assistant:
    build:
      context: .
      args:
        - VITE_DASHSCOPE_API_KEY=your_api_key_here
    ports:
      - "8080:80"
```

#### 方式B：运行时注入

```bash
docker run -d \
  -p 8080:80 \
  -e VITE_DASHSCOPE_API_KEY=your_api_key_here \
  ai-assistant
```

#### 方式C：在浏览器中配置（开发环境）

直接在应用启动后，在浏览器控制台设置：

```javascript
window.VITE_DASHSCOPE_API_KEY = 'your_api_key_here';
```

## 🔧 配置优化

### 端口修改

修改 `docker-compose.yml` 中的端口映射：

```yaml
ports:
  - "3000:80"  # 改为 3000 端口
```

### 性能优化

Nginx 配置已包含：
- ✅ Gzip 压缩
- ✅ 静态资源缓存（30天）
- ✅ SPA 路由支持（解决刷新404）
- ✅ 安全头部配置

## 🐛 故障排查

### 问题1：容器无法启动

```bash
# 查看容器状态
docker ps -a

# 查看容器日志
docker logs ai-assistant
```

### 问题2：无法访问应用

```bash
# 检查端口是否被占用
netstat -ano | findstr :8080

# 检查防火墙
# Windows: 控制面板 -> 系统和安全 -> Windows Defender 防火墙
```

### 问题3：API 调用失败

1. 检查 API 密钥是否正确
2. 检查网络连接
3. 查看浏览器控制台错误
4. 检查 CORS 配置

### 问题4：构建失败

```bash
# 清理 Docker 缓存
docker system prune -a

# 重新构建
docker-compose up -d --build --no-cache
```

## 📊 监控和维护

### 查看资源使用

```bash
# 查看容器资源使用
docker stats ai-assistant

# 查看容器详情
docker inspect ai-assistant
```

### 日志管理

```bash
# 实时查看日志
docker logs -f ai-assistant

# 查看最近 100 行日志
docker logs --tail 100 ai-assistant

# 导出日志
docker logs ai-assistant > app.log
```

## 🔒 安全建议

### 生产环境部署

1. **使用 HTTPS**
   - 在 Nginx 前添加反向代理（如 Traefik）
   - 使用 Let's Encrypt 获取免费 SSL 证书

2. **API 密钥保护**
   - 使用环境变量
   - 不要将密钥提交到代码仓库
   - 定期轮换密钥

3. **访问控制**
   - 添加 IP 白名单
   - 使用防火墙规则

4. **更新维护**
   - 定期更新基础镜像
   - 重新构建和部署

## 🚀 部署到云服务

### 部署到 Docker Hub

```bash
# 登录
docker login

# 标记镜像
docker tag ai-assistant username/ai-assistant:latest

# 推送镜像
docker push username/ai-assistant:latest
```

### 部署到云平台

**阿里云容器服务**
```bash
# 推送到阿里云容器镜像仓库
docker push registry.cn-hangzhou.aliyuncs.com/username/ai-assistant:latest
```

**腾讯云容器服务**
```bash
# 推送到腾讯云容器镜像仓库
docker push ccr.ccs.tencentyun.com/username/ai-assistant:latest
```

## 📝 自定义配置

### 修改 Nginx 配置

编辑 `nginx.conf` 文件，然后重新构建：

```bash
docker-compose up -d --build
```

### 添加环境变量

编辑 `docker-compose.yml`：

```yaml
services:
  ai-assistant:
    environment:
      - NODE_ENV=production
      - VITE_DASHSCOPE_API_KEY=${API_KEY}
```

## 🎯 最佳实践

1. **使用多阶段构建** - 减小镜像大小
2. **启用健康检查** - 自动重启失败的容器
3. **配置日志轮转** - 防止磁盘满
4. **使用 .dockerignore** - 排除不必要的文件
5. **版本控制** - 为镜像打标签

## 📞 技术支持

遇到问题？
1. 查看日志：`docker logs ai-assistant`
2. 检查配置：`nginx.conf`
3. 验证构建：`docker build -t ai-assistant .`
4. 测试本地：先在本地运行 `npm run preview`
