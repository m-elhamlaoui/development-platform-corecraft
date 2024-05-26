# Use official PostgreSQL image from Docker Hub
FROM postgres:latest

# Set environment variables
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=pass123
ENV POSTGRES_MULTIPLE_DATABASES="workoutData,planData,statData,userData"


# Copy custom configuration files, if any
# COPY ./path/to/custom-config.conf /etc/postgresql/conf.d/
COPY ./create_db.sh /docker-entrypoint-initdb.d/create_db.sh
# Expose PostgreSQL default port
EXPOSE 5432

# Start PostgreSQL server on container startup
CMD ["postgres"]
