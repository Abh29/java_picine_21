package com.example.block_chain.db;

import com.example.block_chain.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockChainRepository extends JpaRepository<Block, Long> {
    Block findTopByOrderByIdDesc();
}
