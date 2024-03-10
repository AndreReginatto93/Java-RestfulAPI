## Product API Documentation

This API provides endpoints to manage products in a database. It allows creating, reading, updating, and deleting product records.

### ProductModel Fields

- `productID` (UUID): Unique identifier for each product.
- `name` (String): Name of the product.
- `value` (BigDecimal): Value or price of the product.

### Endpoints

- **POST /products**
  - Creates a new product record.
  - Request body should be a JSON object following the structure of ProductRecordDto.
  - Returns the created product with HTTP status code 201 (Created).

- **GET /products**
  - Retrieves all product records.
  - Returns a list of product records with HTTP status code 200 (OK).

- **GET /product/{id}**
  - Retrieves a specific product by its ID.
  - Requires the ID of the product as a path parameter.
  - Returns the product object if found, else returns "Product not found" with HTTP status code 404 (Not Found).

- **PUT /product/{id}**
  - Updates an existing product record.
  - Requires the ID of the product as a path parameter.
  - Request body should be a JSON object following the structure of ProductRecordDto.
  - Returns the updated product with HTTP status code 200 (OK).

- **DELETE /product/{id}**
  - Deletes a specific product by its ID.
  - Requires the ID of the product as a path parameter.
  - Returns "Product deleted successfully" with HTTP status code 200 (OK) if the product exists, else returns "Product not found" with HTTP status code 404 (Not Found).

### Usage Notes

- All requests and responses are in JSON format.
- Ensure to include proper validation for request payloads to maintain data integrity.
- Handle errors gracefully and provide meaningful error messages in responses.
- This API assumes the existence of a database and interacts with it via the `ProductRepository`.

### Example Usage

#### Create Product
```json
POST /products
Body
{
  "name": "Example Product",
  "value": 10.99
}


Return
{
  "idProduct": "ac597fdf-c45a-4e37-b253-191d9b4f8561",
  "name": "Example Product",
  "value": 10.99
}
```

#### Get All Products
```json
GET /products

Return
[
  {
    "idProduct": "ac597fdf-c45a-4e37-b253-191d9b4f8561",
    "name": "Example Product",
    "value": 10.99
  }
]
```

#### Get Product by ID
```json
GET /product/{id}

Return
{
  "idProduct": "ac597fdf-c45a-4e37-b253-191d9b4f8561",
  "name": "Example Product",
  "value": 10.99
}
```

#### Update Product
```json
PUT /product/{id}
Body
{
  "name": "Updated Product Name",
  "value": 15.99
}

Return
{
  "idProduct": "ac597fdf-c45a-4e37-b253-191d9b4f8561",
  "name": "Updated Product Name",
  "value": 15.99
}
```

#### Delete Product
```json
DELETE /product/{id}

Return
Product deleted successfully
```
