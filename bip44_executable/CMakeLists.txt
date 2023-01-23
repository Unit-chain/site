cmake_minimum_required(VERSION 3.24)
project(bip44_executable)

set(CMAKE_CXX_STANDARD 20)

find_package(OpenSSL REQUIRED)
find_package(Boost REQUIRED)

add_executable(bip44_executable main.cpp)
add_subdirectory(bip44)
target_link_libraries(${PROJECT_NAME} bip44)

target_link_libraries(${PROJECT_NAME} OpenSSL::SSL)
target_link_libraries(${PROJECT_NAME} Boost::boost)
