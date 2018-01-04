# FROM nginx
# # COPY default.conf /etc/nginx/conf.d/default.conf

# RUN rm /etc/nginx/conf.d/default.conf
# COPY conf /etc/nginx
# EXPOSE 80
# CMD ["nginx", "-g", "daemon off;"]


FROM nginx
COPY default.conf /etc/nginx/conf.d/default.conf
COPY ssl/nginx.crt /etc/nginx/ssl/nginx.crt
COPY ssl/nginx.key /etc/nginx/ssl/nginx.key
COPY ssl/localhost.key /etc/nginx/ssl/localhost.crt
COPY ssl/localhost.key /etc/nginx/ssl/localhost.key
# EXPOSE 80
# RUN rm /etc/nginx/conf.d/default.conf
# COPY conf /etc/nginx
# EXPOSE 80
# CMD ["nginx", "-g", "daemon off;"]