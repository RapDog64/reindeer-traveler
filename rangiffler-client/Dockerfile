FROM node:19.4.0-alpine as build
ARG NPM_COMMAND
WORKDIR /app
COPY package.json ./
RUN apk add --no-cache python3 make g++
RUN npm install
COPY . ./
RUN npm run ${NPM_COMMAND}

# release step
FROM nginx:1.23.3-alpine as release
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist /usr/share/nginx/html/
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]