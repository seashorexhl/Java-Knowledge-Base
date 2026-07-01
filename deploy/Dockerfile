# 使用轻量级的 Nginx Alpine 镜像
FROM nginx:alpine

# 维护者信息（可选）
LABEL maintainer="your-name"

# 1. 清理默认配置和欢迎页
RUN rm -rf /usr/share/nginx/html/* && \
    rm -rf /etc/nginx/conf.d/default.conf

# 2. 复制前端静态资源
# 假设你的前端打包产物在根目录的 dist 文件夹，或者你想直接展示某些 html
# 注意：这里的 ../dist 是相对于 Dockerfile 所在路径（deploy/）的上一级
COPY ../dist /usr/share/nginx/html/

# 3. 复制并应用你的 Nginx 自定义配置
# 将你精心配置的 nginx.conf 覆盖到容器内的标准位置
COPY ./nginx/nginx.conf /etc/nginx/nginx.conf

# 如果还有子配置文件（如 conf.d/*.conf），也可以一并复制
# COPY ./nginx/conf.d/ /etc/nginx/conf.d/

# 4. 暴露端口
EXPOSE 80

# 5. 启动 Nginx (前台运行模式，防止容器启动后立即退出)
CMD ["nginx", "-g", "daemon off;"]