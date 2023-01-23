#include <iostream>
#include "bip44/BIP44.hpp"
#include "bip44/ecdsa.hpp"

using namespace std;

int main(int argc, char *argv[]) {
    std::string password = argv[1];
    BIP44 bip44;
    BIP44Result r = bip44.generateWallet(PHRASE_24, 0, EXTERNAL_CHANGE, password);
    cout << R"({"WALLET_PATH": ")" << r.path << R"(",)" << endl;
    cout << R"("WALLET_PHRASE": ")" << r.mnemonic.phrase << R"(",)" << endl;
    cout << R"("WALLET_SEED": ")" << r.mnemonic.seed << R"(",)" << endl;
    cout << R"("WALLET_PRV": ")" << r.prv << R"(",)" << endl;
    cout << R"("WALLET_PUB": ")" << r.pub << R"(",)" << endl;
    cout << R"("WALLET_E_PRV": ")" << r.extended_prv << R"(",)" << endl;
    cout << R"("WALLET_E_PUB": ")" << r.extended_pub << R"(",)" << endl;
    cout << R"("WALLET_ADDRESS": ")" << r.address << R"(",)" << endl;

    cout << R"("Signing_message_Hello": {)" << endl;
    ECDSASignResult sig = ecdsa_sign_message("Hello!", r.prv);
    std::cout << R"("r":")" << sig.r << R"(",)" << std::endl;
    std::cout << R"("s":")" << sig.s << "\"" << std::endl << R"(},)"<< std::endl;
    bool verified = ecdsa_verify_signature(sig.r, sig.s, "Hello!", r.address);
    cout << R"("Result of verification": )"<<verified<<"\n}";
    return 0;
}
