#include <iostream>
#include "bip44/BIP44.hpp"
#include "bip44/ecdsa.hpp"

using namespace std;

int main() {
    BIP44 bip44;
    BIP44Result r = bip44.generateWallet(PHRASE_24, 0, EXTERNAL_CHANGE);
    cout << "WALLET PATH: " << r.path << endl;
    cout << "WALLET PHRASE: " << r.mnemonic.phrase << endl;
    cout << "WALLET SEED: " << r.mnemonic.seed << endl;
    cout << "WALLET PRV: " << r.prv << endl;
    cout << "WALLET PUB: " << r.pub << endl;
    cout << "WALLET E_PRV: " << r.extended_prv << endl;
    cout << "WALLET E_PUB: " << r.extended_pub << endl;
    cout << "WALLET ADDRESS: " << r.address << endl;

    cout << "\nSigning message 'Hello!': \n";
    ECDSASignResult sig = ecdsa_sign_message("Hello!", r.prv);
    std::cout << "r: " << sig.r << std::endl;
    std::cout << "s: " << sig.s << std::endl;
    bool verified = ecdsa_verify_signature(sig.r, sig.s, "Hello!", r.address);
    cout << "Result of verification: "<<verified<<"\n";
    return 0;
}
