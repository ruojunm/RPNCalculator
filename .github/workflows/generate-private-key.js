const ethers = require('ethers');

// Generate a random private key
const privateKey = ethers.Wallet.createRandom().privateKey;

console.log(`Random Ethereum private key: ${privateKey}`);

// Create a wallet instance from the private key
const wallet = new ethers.Wallet(privateKey);

// Print the account address
console.log(`Account address: ${wallet.address}`);