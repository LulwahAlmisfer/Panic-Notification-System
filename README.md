<h1>Panic Notification System</h1>

a notification system utilizes a fast callback mechanism through a message queue in a Microservices setup, ensuring rapid and reliable delivery of alerts for real-time applications.

<h2>Architecture Design</h2>
<img src="panic-architecture-design.png" width="640" height="320"> 

<h2>Database</h2>
<img src="Panic_db.png" width="400" height="300"> 


<h2>Deployment ☁️ </h2>
<img src="GCP.png" width="640" height="320"> 

- **4 services** as containers using Google Cloud Run.
- **PostgreSQL database** hosted on Google Cloud's **Cloud SQL**.
- **RabbitMQ** installed on Google Compute Engine.
- **Cloud Function** for testing callback, triggered by HTTP requests.

