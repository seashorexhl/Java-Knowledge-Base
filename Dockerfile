# 使用轻量级的 Nginx 镜像
FROM nginx:alpine

# 删除 Nginx 默认的欢迎页面
RUN rm -rf /usr/share/nginx/html/*

# 把你本地的两个 html 文件复制到 Nginx 的网站根目录
COPY . /usr/share/nginx/html/

# 暴露 80 端口
EXPOSE 80

# 启动 Nginx
CMD ["nginx", "-g", "daemon off;"]