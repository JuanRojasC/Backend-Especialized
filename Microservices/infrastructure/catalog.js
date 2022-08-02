db = db.getSiblingDB("catalog_database")
db.createCollection("catalog")

db.createUser({
    user: "superuser",
    pwd: "superuser",
    roles: [
        {
            role: "dbOwner",
            db: "catalog_database"
        }
    ],
});

db.createUser({
    user: "catalog",
    pwd: "catalog",
    roles: [
        {
            role: "readWrite",
            db: "catalog_database"
        }
    ],
});