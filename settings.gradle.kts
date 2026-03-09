rootProject.name = "toy-msa"

include(
    ":service-discovery",
    ":config-service",
    ":apigateway-service",
    ":common:data-serializer",
    ":common:event",
    ":user-service",
    ":catalog-service",
    ":order-service",
    ":first-service",
    ":second-service",
)