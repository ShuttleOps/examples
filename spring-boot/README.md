# sample-spring-app

This Chef Habitat package integrates the Spring Boot Actuator with [Chef Habitat's health-check hook](https://www.habitat.sh/docs/reference/application-lifecycle-hooks/). Once this package is deployed to AWS via ShuttleOps onto multiple EC2 instances behind an ELB, the health-check hook is honored by the ELB health checks.

See [Managing Spring Boot Applications using Chef Habitat](https://www.shuttleops.io/managing-spring-boot-applications-using-chef-habitat) for a full blog post and video tutorial showcasing this package and its use with ShuttleOps. 
