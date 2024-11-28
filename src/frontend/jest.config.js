module.exports = {
    transform: {
      '^.+\\.vue$': 'vue3-jest',
      '^.+\\.(js|jsx)$': 'babel-jest',
    },
    testEnvironment: 'jsdom',
    moduleFileExtensions: ['js', 'vue', 'json'],
    moduleNameMapper: {
      '^@/(.*)$': '<rootDir>/src/$1',
    },
    transformIgnorePatterns: [
      '/node_modules/(?!@vue|vue-router)',
    ],
    testMatch: [
      '**/test/app.test.js'
    ],
    setupFiles: ['<rootDir>/jest.setup.js'],
  };