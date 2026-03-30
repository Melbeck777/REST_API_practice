Feature: Books API Verification with DB Comparison

  Background:
    * url baseUrl

  Scenario: Get all books and verify against database
    # 1. Fetch data from DB
    * def dbBooks = db.readAll('SELECT id, title, author, price, publisher FROM BOOK ORDER BY id')
    * print 'Database Results:', dbBooks

    # 2. Call API
    Given path '/books'
    When method get
    Then status 200
    * def apiBooks = response
    * print 'API Results:', apiBooks

    # 3. Compare counts
    And assert apiBooks.length == dbBooks.length

    # 4. Compare specific fields for each book
    # Note: Karate matches ignore order by default if it's an array of objects
    # but since we ordered by id, we can compare directly or use karate.match
    * match each apiBooks ==
    """
    {
      id: '#number',
      title: '#string',
      author: '#string',
      publishDate: '#string',
      price: '#number',
      publisher: '#string'
    }
    """

    # Comprehensive check: ensure all DB records are present in the API response
    # We mapping DB keys (lowercase from ResultSet) to API keys if necessary.
    # Our DbUtils uses lowercase keys.
    * def expected = karate.map(dbBooks, function(x){ return { id: x.id, title: x.title, author: x.author, price: x.price, publisher: x.publisher } })
    * match apiBooks contains deep expected

  Scenario: Get single book by ID and verify against database
    # 1. Fetch a record from DB to test with
    * def firstBook = db.readAll('SELECT * FROM BOOK LIMIT 1')[0]
    * def bookId = firstBook.id

    # 2. Call API for that specific ID
    Given path '/books', bookId
    When method get
    Then status 200
    * def apiBook = response

    # 3. Compare API response with DB record
    # API fields: id, title, author, publishDate, price, publisher
    # DB fields (from DbUtils): id, title, author, publish_date, price, publisher
    And match apiBook.id == firstBook.id
    And match apiBook.title == firstBook.title
    And match apiBook.author == firstBook.author
    And match apiBook.price == firstBook.price
    And match apiBook.publisher == firstBook.publisher
    # Date comparison might need formatting but usually we can check prefix
    And match apiBook.publishDate contains firstBook.publish_date.toString().substring(0, 10)
