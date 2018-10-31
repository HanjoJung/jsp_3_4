package com.jhj.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jhj.util.DBConnector;

public class MemberDAO {
	public MemberDTO login(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select id, pw, name, email, kind, m.classMate, grade, ban from member m " + 
				"LEFT JOIN team t on (m.classmate = t.classmate) where id=? and pw=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			
		}
		DBConnector.disConnect(rs, st, con);
		return memberDTO;
	}
	public MemberDTO logout(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select id, pw, name, email, kind, m.classMate, grade, ban from member m " + 
				"LEFT JOIN team t on (m.classmate = t.classmate) where id=? and pw=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			
		}
		DBConnector.disConnect(rs, st, con);
		return memberDTO;
	}
	public MemberDTO selectOne(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select id, pw, name, email, kind, m.classMate, grade, ban from member m " + 
				"LEFT JOIN team t on (m.classmate = t.classmate) where id=? and pw=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			
		}
		DBConnector.disConnect(rs, st, con);
		return memberDTO;
	}
	public int join(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="insert into member values(?,?,?,?,?,?)";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getName());
		st.setString(4, memberDTO.getEmail());
		st.setString(5, memberDTO.getKind());
		st.setString(6, memberDTO.getClassMate());
		int result = st.executeUpdate();
		
		DBConnector.disConnect( st, con);
		return result;
	}
	public int update(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="update member set pw=?, name=?, email=?, kind=?, classmate=? where id=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getPw());
		st.setString(2, memberDTO.getName());
		st.setString(3, memberDTO.getEmail());
		st.setString(4, memberDTO.getKind());
		st.setString(5, memberDTO.getClassMate());
		st.setString(6, memberDTO.getId());
		int result = st.executeUpdate();
		
		DBConnector.disConnect( st, con);
		return result;
	}
	public int delete(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="delete member where id=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		int result = st.executeUpdate();
		
		DBConnector.disConnect( st, con);
		return result;
	}
}
