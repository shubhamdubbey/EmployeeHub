# EmployeeHub - HR Management System 🏢

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-green)
![Angular](https://img.shields.io/badge/Angular-15-red)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Docker](https://img.shields.io/badge/Docker-✓-blue)

A modern, full-stack Human Resource Management System built with **Spring Boot 3.3.4** and **Angular 15**. Streamline your HR operations with automated employee management, leave processing, and approval workflows in a secure, containerized environment.

## ✨ Key Features

| Module | Features |
|--------|----------|
| **👥 Employee Management** | Create, update, delete employees • Grade-based hierarchy • Manager assignment • Role-based access |
| **🏝 Leave Management** | Sick, Casual, Earned, Paternity leaves • Real-time balance tracking • Application workflow • Historical audit |
| **✅ Approval System** | Leave approvals • Manager change requests • Pending requests dashboard • Status tracking |
| **🔐 Security** | JWT authentication • BCrypt encryption • Role-based access • First-login password change |

## 🚀 Quick Deployment

### Option 1: Docker (Recommended)
```bash
# Clone and deploy in one command
git clone https://github.com/shubhamdubbey/EmployeeHub.git
cd EmployeeHub
docker-compose up -d
```

**Services will be available at:**
- 🌐 **Backend API**: http://localhost:8090
- 💾 **PostgreSQL**: localhost:5432
- 🎯 **Redis**: localhost:6379

### Option 2: Manual Setup
```bash
# Backend
mvn clean install
mvn spring-boot:run

# Frontend (new terminal)
cd frontend
npm install
ng serve
```

Frontend will be available at http://localhost:4200

## 🛠 Tech Stack

**Backend**
- **Framework**: Spring Boot 3.3.4
- **Security**: Spring Security 6 + JWT
- **Database**: PostgreSQL with Hibernate JPA
- **Cache**: Redis for performance
- **Validation**: Bean Validation API
- **Build**: Maven

**Frontend**
- **Framework**: Angular 15
- **UI**: Angular Material
- **State**: RxJS Observables
- **HTTP**: HttpClient

**Infrastructure**
- **Containerization**: Docker & Docker Compose
- **Database**: PostgreSQL 15
- **Cache**: Redis 7

## 📡 Core API Endpoints

| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| `POST` | `/api/login` | All | JWT Authentication |
| `POST` | `/api/changePassword` | All | Password management |
| `GET` | `/api/getEmployees` | HR, ADMIN | Paginated employee list |
| `POST` | `/api/addEmployee` | HR, ADMIN | Create new employee |
| `POST` | `/api/applyLeaves` | HR,ADMIN,EMP | Submit leave request |
| `GET` | `/api/listOfApproval/{id}` | HR, ADMIN | Pending approvals |
| `PUT` | `/api/updateApproval/{id}/{status}` | HR, ADMIN | Process approvals |

## 🗂 Project Structure

```
EmployeeHub/
├── 📁 backend/                 # Spring Boot Application
│   ├── controller/            # REST APIs
│   ├── service/              # Business logic
│   ├── repository/           # Data access
│   ├── entity/               # JPA entities
│   └── dto/                  # Data transfer objects
├── 📁 frontend/              # Angular Application
│   ├── components/           # UI components
│   ├── services/             # API services
│   └── models/               # TypeScript models
└── 🐳 docker-compose.yml     # Multi-container setup
```

## ⚙️ Configuration

### Database Setup
The application automatically creates the schema. Key entities:
- **Users** - Employee master data
- **Leaves** - Leave balances  
- **LeaveTracker** - Leave history
- **Approvals** - Approval workflows
- **Grades** - Organizational hierarchy

### Environment Variables
```properties
# Database
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/human-resource
SPRING_DATASOURCE_USERNAME=human-resource
SPRING_DATASOURCE_PASSWORD=human-resource

# JWT Security
JWT_SECRET=your-jwt-secret-key

# Redis
SPRING_REDIS_HOST=localhost
SPRING_REDIS_PORT=6379
```

## 🔐 Security Features

- **JWT Tokens** - Stateless authentication
- **Role-based Access** - HR, ADMIN, EMPLOYEE roles
- **Password Encryption** - BCrypt hashing
- **Input Validation** - Bean validation annotations
- **CORS Protection** - Configured for frontend

## 🚢 Docker Services

| Service | Image | Port | Purpose |
|---------|-------|------|---------|
| `human_resource_app` | Spring Boot | 8090 | Main application |
| `postgres_db` | PostgreSQL 15 | 5432 | Primary database |
| `redis` | Redis 7 | 6379 | Caching layer |

## 🤝 Contributing

We welcome contributions! Please feel free to submit pull requests or open issues for bugs and feature requests.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 💬 Support

For support, email shubhamdubbey@gmail.com or create an issue in the repository.

---

**Built with ❤️ using Spring Boot & Angular**

*If you find this project helpful, don't forget to give it a ⭐!*
