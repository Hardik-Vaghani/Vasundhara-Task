package com.hardik.vasundharatask.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hardik.vasundharatask.R
import com.hardik.vasundharatask.databinding.FragmentSecondBinding
import com.hardik.vasundharatask.presentation.adapter.GridAdapter
import com.hardik.vasundharatask.presentation.model.ChessboardModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private lateinit var someData: String
    lateinit var gridAdapter: GridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        someData = requireArguments().getString("someData", "")
        Log.e("TAG", "onCreate: $someData")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridAdapter = GridAdapter()
        setRecyclerView()
        val matrix = mutableListOf<ChessboardModel>()

        for (i in 0 until someData.toInt()) {
            for (j in 0 until someData.toInt()) {
                matrix.add(ChessboardModel(i, j))
            }
        }
        gridAdapter.updateData(matrix)

        gridAdapter.setOnItemClickListener { chessboardModel ->
            // first clear matrix
            for (i in 0 until matrix.size) {
                matrix[i] = matrix[i].also { chessboardItem ->
                    chessboardItem.isActive = false
                }
            }

            // do your work
            if (chessboardModel.isActive) {
                for (i in 0 until matrix.size) {
                    matrix[i] = matrix[i].also { chessboardItem ->
                        if (chessboardItem.row == chessboardModel.row) {
                            chessboardItem.isActive = true
                        }
                        if (chessboardItem.col == chessboardModel.col) {
                            chessboardItem.isActive = true
                        }
                        if (chessboardItem.row == chessboardModel.row && chessboardItem.col == chessboardModel.col || chessboardItem.row - chessboardItem.col == chessboardModel.row - chessboardModel.col || chessboardItem.row + chessboardItem.col == chessboardModel.row + chessboardModel.col) {
                            chessboardItem.isActive = true
                        }
                    }
                }
            }
            gridAdapter.updateData(matrix)
        }
    }


    private fun setRecyclerView() {
        binding.recyclerView.apply {
            adapter = gridAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), someData.toInt(), RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.HORIZONTAL))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
