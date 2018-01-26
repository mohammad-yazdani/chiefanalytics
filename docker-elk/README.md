Follow these steps to replicate my example

1. Download and install Docker from [here](https://docs.docker.com/engine/installation/)
2. Pull this branch and cd into the docker-elk directory
3. In your terminal, run `docker-compose up -d`
4. Wait for a few minutes and head to `http://localhost:5601`
5. Log in with username: `elastic` and password `changeme`
6. In your terminal, run `nc localhost 5000 < cars.csv`
7. Head back to the Kibana dashboard and head to the Discover tab
8. Elasticsearch should have indexed cars.csv with the index name cars. If not, repeat the steps above.
9. Create the new index pattern, head back to the Discover tab, and explore the extracted data
