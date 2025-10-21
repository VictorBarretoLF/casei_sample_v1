package config;

import org.testcontainers.utility.DockerImageName;

public class BaseIntegrationTest {

    DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:15");

}
