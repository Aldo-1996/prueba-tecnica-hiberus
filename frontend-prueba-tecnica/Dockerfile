# Etapa 1: build Angular app
FROM node:18-alpine as build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Etapa 2: servir con Nginx
FROM nginx:alpine
COPY --from=build /app/dist/frontend-prueba-tecnica /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf


