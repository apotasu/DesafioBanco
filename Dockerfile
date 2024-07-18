FROM postgres:latest

ENV POSTGRES_DB=pessoa
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=password

COPY init-db.sh /docker-entrypoint-initdb.d/
RUN chmod +x /docker-entrypoint-initdb.d/init-db.sh

EXPOSE 5432
