cd ./BackAppOnDocker/Backend
mvn package
cd ./BankAppOnDocker/frontend
npm install
npm build
cd ./BankAppOnDocker
docker-compose up --build
